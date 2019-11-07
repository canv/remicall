  create table user_subscriptions (
    channel_id uuid not null references user_data,
    subscriber_id uuid not null references user_data,
    primary key (channel_id, subscriber_id)
)